package com.carag.dataserver.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(("classpath:credentialstore.properties"))
public class CredentialStore {

    @Value("${lloyd.username}")
    private String lloydUser;

    @Value("${lloyd.password}")
    private String lloydPass;


    public String getLloydUser() {
        return lloydUser;
    }

    public void setLloydUser(String lloydUser) {
        this.lloydUser = lloydUser;
    }

    public String getLloydPass() {
        return lloydPass;
    }

    public void setLloydPass(String lloydPass) {
        this.lloydPass = lloydPass;
    }
}
