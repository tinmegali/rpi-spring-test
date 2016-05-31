package com.tinmegali.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	
	private static GpioPinDigitalOutput pin;
	
	@RequestMapping("/")
	public String greeting(){
		return "Hello World!";
	}
	
	@RequestMapping("/light")
	public String light() {
		
		if ( pin == null) {
			// getting GPIO controller
			GpioController gpioController = GpioFactory.getInstance();
			
			// Setting pin output
			pin = 
					gpioController.provisionDigitalOutputPin(
							RaspiPin.GPIO_01, "MyLED", PinState.LOW
							);
		}
		
		pin.toggle();
		return "OK";
	}
}
