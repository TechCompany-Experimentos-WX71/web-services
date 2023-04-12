package com.fastporte.fastportewebservice.controller;

import com.fastporte.fastportewebservice.entities.*;
import com.fastporte.fastportewebservice.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;

    }

    //Retornar todos los comments
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> findAllComments() { //Response entity: la clase por defecto de spring para responder desde un controlador de API
        try {
            List<Comment> comments = commentService.getAll();

            if (comments.size() > 0)
                return new ResponseEntity<>(comments, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Retornar contrato por id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> findContractById(@PathVariable("id") Long id) {
        try {
            Optional<Comment> comment = commentService.getById(id);

            if (comment.isPresent()) //con isPresent se valida si es de tipo Contract o es nulo
                return new ResponseEntity<>(comment.get(), HttpStatus.OK); //se usa get porque es optional
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Retornar commentario por id
/*
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> findCommentById(@PathVariable("id") Long id) {
        try {
            Optional<Comment> comment = commentService.getById(id);

            if (comment.isPresent()) //con isPresent se valida si es de tipo comment o es nulo
                return new ResponseEntity<>(comment.get(), HttpStatus.OK); //se usa get porque es optional
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

    //Retornar los commentarios por driver id
    @GetMapping(value = "/driver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> findCommentById( @PathVariable("id") Long id) {
        try {
            List<Comment> comments = commentService.getAll();

            comments.removeIf(comment -> !comment.getDriver().getId().equals(id));
            if (comments.size() > 0)
                return new ResponseEntity<>(comments, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    //Retornar comentarios por id de driver
    @GetMapping(value = "/driver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> findCommentByDriverId(@PathVariable("id") Long id) {
        try {
            List<Comment> comments = commentService.gettAll();
            comments.removeIf(comment -> !comment.getDriver().getId().equals(id));
            if (comments.size() > 0)
                return new ResponseEntity<>(comments, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

}
