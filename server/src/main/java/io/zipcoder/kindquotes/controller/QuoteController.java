package io.zipcoder.kindquotes.controller;

import io.zipcoder.kindquotes.model.Quote;
import io.zipcoder.kindquotes.repository.QuoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.ArrayList;

@RequestMapping("/quotes")
@RestController
@CrossOrigin//("http://localhost:8100")
public class QuoteController {

    @Inject
    private QuoteRepository quoteRepository;

    /*
    @RequestMapping("/")
    public ResponseEntity<ArrayList<Quote>> getAllQuotes(){
        Quote quote = new Quote("Wu Tang Clan!!!");
        ArrayList<Quote> quotes = new ArrayList<>();
        quotes.add(quote);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }
    */

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseEntity<ArrayList<Quote>> getAllQuotes() {
        if(quoteRepository.count() > 0) {
            ArrayList<Quote> allQuotes = (ArrayList<Quote>) quoteRepository.findAll();
            return new ResponseEntity<>(allQuotes, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{quoteId}", method= RequestMethod.GET)
    public ResponseEntity<Quote> getQuote(@PathVariable Long quoteId) {
        if(quoteRepository.exists(quoteId)) {
            Quote quote = quoteRepository.findOne(quoteId);
            return new ResponseEntity<>(quote, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote, UriComponentsBuilder ucBuilder){
        quote = quoteRepository.save(quote);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}")
                .buildAndExpand(quote.getId())
                .toUri());
        return new ResponseEntity<>(quote, headers, HttpStatus.CREATED);
    }


    /*
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> createQuote(@RequestBody Quote quote){
        quote = quoteRepository.save(quote);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(quote.getId())
                .toUri();
        responseHeaders.setLocation(newQuoteUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    */


    @RequestMapping(value = "/{quoteId}", method = RequestMethod.PUT)
    public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote, @PathVariable Long quoteId){
        if(quoteRepository.exists(quoteId)) {
            quote = quoteRepository.save(quote);
            return new ResponseEntity<>(quote, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{quoteId}", method = RequestMethod.DELETE)
    public ResponseEntity<Quote> deleteQuote(@PathVariable Long quoteId) {
        quoteRepository.delete(quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.DELETE)
    public ResponseEntity<Quote> deleteAllQuotes() {
        if(quoteRepository.count() > 0) {
            quoteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
