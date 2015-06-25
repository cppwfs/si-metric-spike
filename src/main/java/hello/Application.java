package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.integration.SpringIntegrationMetricReader;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.monitor.IntegrationMBeanExporter;

@SpringBootApplication
@ImportResource("/integration.xml")
@EnableAutoConfiguration(exclude = {JmxAutoConfiguration.class, IntegrationAutoConfiguration.class})
public class Application {
	
	@Autowired
	RedisConnectionFactory connectionFactory;
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

	@Bean
	@ExportMetricWriter
	MetricWriter metricWriter(MetricExportProperties export) {
		return new RedisMetricRepository(connectionFactory,
				export.getRedis().getPrefix(), export.getRedis().getKey());
	}

	@Bean
	@ExportMetricReader
	public SpringIntegrationMetricReader springIntegrationMetricReader(
			IntegrationMBeanExporter exporter) {
		return new SpringIntegrationMetricReader(exporter);
	}

}
