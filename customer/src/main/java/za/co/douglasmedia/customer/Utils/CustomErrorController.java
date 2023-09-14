package za.co.douglasmedia.customer.Utils;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
    // when an un-existing URL is hit we get this error
    @RequestMapping("/error")
    public ResponseEntity<CustomerResponse> handleError() {
        CustomerResponse response = new CustomerResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("URL does not exist");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public String getErrorPath() {
        return "/error";
    }
}
