package dev.tanvx.customerservice.util;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageUtils {

  private final MessageSource messageSource;

  public String getMessage(String code, Object[] args){
    return messageSource.getMessage(code, args, Locale.getDefault());
  }
}
