package cn.edu.buaa.springmvc.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BetweenValidator implements ConstraintValidator<Between, Integer> {
	
	int min;
	int max;
	
	@Override
	public void initialize(Between constraintAnnotation) {
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value != null && min <= value && value <= max) {
			return true;
		}
		
		return false;
	}

}
