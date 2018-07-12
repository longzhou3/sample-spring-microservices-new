package pl.piomin.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
/*@EnableCircuitBreaker
@EnableHystrix*/
public class GatewayApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/**
	 * 自定义限流标志的key，多个维度可以从这里入手
	 * exchange对象中获取服务ID、请求信息，用户信息等
	 */
	@Bean
	KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
}
