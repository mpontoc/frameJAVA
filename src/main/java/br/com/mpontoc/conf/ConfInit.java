package br.com.mpontoc.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
		basePackages = { 
				"br.sp.mpontoc.config", 
		} 
		)
//@PropertySource("application.properties")
@EntityScan(
		basePackages = {
				"br.sp.mpontoc.config",
		}
		)

public class ConfInit {


}


