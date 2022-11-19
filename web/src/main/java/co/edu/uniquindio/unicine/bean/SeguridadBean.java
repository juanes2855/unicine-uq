package co.edu.uniquindio.unicine.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {
}
