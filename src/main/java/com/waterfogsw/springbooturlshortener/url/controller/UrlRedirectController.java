package com.waterfogsw.springbooturlshortener.url.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.waterfogsw.springbooturlshortener.url.serivce.UrlService;

@RestController
public class UrlRedirectController {

  private static final Logger log = LoggerFactory.getLogger(UrlRedirectController.class);
  private final UrlService urlService;

  public UrlRedirectController(UrlService urlService) {
    this.urlService = urlService;
  }

  @GetMapping("{key}")
  public void redirect(
      HttpServletResponse response,
      @PathVariable @NotEmpty String key
  ) {
    final var redirectUri = urlService.getRedirectUrl(key);

    try {
      response.sendRedirect(redirectUri);
    } catch (IOException e) {
      log.warn(e.getMessage());
      throw new IllegalStateException(e.getMessage());
    }
  }
}
