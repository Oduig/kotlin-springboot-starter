package com.github.oduig.kotlin.springboot.starter.config

// Example authentication setup (with an auth0 provider)
//@Configuration
//@EnableWebSecurity
//@Import(SecurityProblemSupport::class)
//class SpringWebSecurityAdapter
//      (private val auth0AuthenticationProvider: AuthenticationProvider,
//       private val problemSupport: SecurityProblemSupport): WebSecurityConfigurerAdapter()
//{
//
//  companion object: KLogging()
//
//  override fun configure(http: HttpSecurity) {
//    JwtWebSecurityConfigurer
//        .forRS256("https://myproject.com/api", "https://sencorsoft.eu.mycompany.com/", auth0AuthenticationProvider)
//        .configure(http)
//        .csrf().disable()
//        .cors()
//        .and().authorizeRequests()
//        .antMatchers("/", "/api", "/api/v1").permitAll()
//        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .anyRequest().fullyAuthenticated()
//
//    http.exceptionHandling()
//        .authenticationEntryPoint(problemSupport)
//        .accessDeniedHandler(problemSupport)
//  }
//}
