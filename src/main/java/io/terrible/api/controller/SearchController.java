/* Licensed under Apache-2.0 */
package io.terrible.api.controller;

import io.terrible.api.domain.MediaFile;
import io.terrible.api.services.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

  private final SearchService searchService;

  @GetMapping("/index")
  public Mono<String> index() {

    return searchService.createIndex("media").then(searchService.populate("media"));
  }

  @GetMapping
  public Flux<MediaFile> search(@RequestParam final String query) {

    return searchService.search(query);
  }

  @DeleteMapping
  public Mono<Void> deleteAll() {

    return searchService.deleteIndex();
  }
}
