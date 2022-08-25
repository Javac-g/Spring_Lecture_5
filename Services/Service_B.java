package com.company.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Primary
@Service("Service_B")
@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Service_B implements Service_Impl{
    @Value("${prop.B}")
    private String one;

    @Override
    public String getOne(){
        return one;
    }
}
