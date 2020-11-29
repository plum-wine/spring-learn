package groovy;

import com.github.service.GroovyBeanCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.lang.invoke.MethodHandles;

public class FoobarCommand implements GroovyBeanCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    String run() {
        LOGGER.info("foobar command");
        return "FoobarCommand Data";
    }

}
