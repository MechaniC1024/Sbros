package com.PrestaShop.InitialConfiguration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browsers {
	
	ANDROID{
        public DesiredCapabilities create(){
        	
        	DesiredCapabilities cap = DesiredCapabilities.android();
        	cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "FBAZCY03Z195");
        	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        	cap.setCapability(MobileCapabilityType.VERSION, "5.0.2");
            return cap;
        }
    },
	CHROME {
        public DesiredCapabilities create(){
        	
        	WebDriverManager.chromedriver().setup();
            return DesiredCapabilities.chrome();
        }
    },
    FIREFOX {
        public DesiredCapabilities create() {
        	
        	WebDriverManager.firefoxdriver().setup();
            return DesiredCapabilities.firefox();
        }
    };

    public DesiredCapabilities create(){
        return null;
    }
}
