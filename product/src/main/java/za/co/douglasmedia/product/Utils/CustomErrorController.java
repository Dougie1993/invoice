package za.co.douglasmedia.product.Utils;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ProductResponse> handleError() {
        ProductResponse response = new ProductResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Bad Request Make sure URL and Payload is correct ");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public String getErrorPath() {
        return "/error";
    }
}

