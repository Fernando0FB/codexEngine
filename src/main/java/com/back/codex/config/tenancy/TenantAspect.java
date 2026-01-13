package com.back.codex.config.tenancy;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@Aspect
@Configuration
@RequiredArgsConstructor
public class TenantAspect {

    private final EntityManager entityManager;

    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.find*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.get*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.findAll(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.count(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.exists*(..))")
    public void setFiltroTenant() {
        String tenant = TenantContext.getCurrentTenant();

        Session sessao = entityManager.unwrap(Session.class);
        sessao.enableFilter("tenantFilter").setParameter("tenant", tenant);
    }

    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.save*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.insert*(..)) || " +
            "execution(* org.springframework.data.jpa.repository.JpaRepository+.create*(..))")
    public void setTenantOnCreate(JoinPoint joinPoint) throws IllegalAccessException {
        String tenant = TenantContext.getCurrentTenant();
        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) {
                continue;
            }

            Class<?> clazz = arg.getClass();

            while (clazz != null && clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(TenantField.class)) {
                        try {
                            field.setAccessible(true);
                            Object currentValue = field.get(arg);

                            if (currentValue == null) {
                                field.set(arg, tenant);
                            }
                        } catch (IllegalAccessException e) {
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            }
        }

    }
}