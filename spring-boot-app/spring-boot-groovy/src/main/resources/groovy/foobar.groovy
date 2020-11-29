import com.github.service.FoobarService
import org.springframework.beans.factory.annotation.Autowired

public class FoobarScript {

    @Autowired
    private FoobarService fooService;

    public String run() {
        String data = fooService.query();
        if (data != null) {
            return data;
        }
        return "null data";
    }

}