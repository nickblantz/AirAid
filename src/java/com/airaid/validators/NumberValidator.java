/*
 * Created by Viet Doan on 2018.11.08  * 
 * Copyright © 2018 Viet Doan. All rights reserved. * 
 */
package com.airaid.validators;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 *
 * @author VDoan
 */

@FacesValidator("numberValidator")
public class NumberValidator implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String num = (String) value;
        int n = Integer.valueOf(num);
        if (num.isEmpty() || num == null)
        {
            return;
        }
        if (n > 0 && n <= 10)
        {
            
        }
        else
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                        "Specified Attribute is not between the expected values of 1 to 10", ""));
        }

    }
    
    
}
