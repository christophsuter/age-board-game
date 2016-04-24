package ch.hslu.testing;

import ch.hslu.testing.boundry.AgeBoardGameResource;
import ch.hslu.testing.domain.BoardGameEngine;
import ch.hslu.testing.domain.BoardGameEngineImpl;
import ch.hslu.testing.internal.AuthenticationConfiguration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.TimeZone;


/**
 * Created by Christoph on 22.04.2016.
 */
public class AgeBoardGameApp extends Application<AuthenticationConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(AgeBoardGameApp.class);
    private Injector injector;

    public static void main(String... args) throws Exception {
        new AgeBoardGameApp().run(args);
    }


    @Override
    public void initialize(final Bootstrap<AuthenticationConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));

        bootstrap.getObjectMapper().setTimeZone(TimeZone.getDefault());
        bootstrap.getObjectMapper().setDateFormat(new StdDateFormat().withTimeZone(TimeZone.getTimeZone("UTC")));

        bootstrap.getObjectMapper().configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        bootstrap.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        bootstrap.getObjectMapper().configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        bootstrap.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        bootstrap.addBundle(new AssetsBundle("/apidocs", "/doc", "index.html", "apidocs"));
    }

    @Override
    public void run(AuthenticationConfiguration conf, Environment env) {
        // setup CORS
        FilterRegistration.Dynamic filter = env.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        injector = createInjector(conf, env);
        env.jersey().register(injector.getInstance(AgeBoardGameResource.class));

        LOG.info("Application up and running.");
    }

    private Injector createInjector(final AuthenticationConfiguration conf,
                                    final Environment env) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(AuthenticationConfiguration.class).toInstance(conf);
                bind(BoardGameEngine.class).to(BoardGameEngineImpl.class);
                bind(ObjectMapper.class).toInstance(env.getObjectMapper());
            }
        });
    }
}
