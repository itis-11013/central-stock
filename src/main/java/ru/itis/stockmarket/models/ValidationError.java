package ru.itis.stockmarket.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * Date: 14.05.2022
 * Time: 11:57 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {
    private Long timestamp;
    private String error;
    private int status;
    private String path;
    private String message;
    private Map<String,String> messages;
}
