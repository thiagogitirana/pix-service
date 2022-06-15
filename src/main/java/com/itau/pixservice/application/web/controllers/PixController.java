package com.itau.pixservice.application.web.controllers;

import com.itau.pixservice.application.web.dto.request.ClientDTO;
import com.itau.pixservice.application.web.dto.response.PixResponseDTO;
import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.services.PixService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> addPix(@RequestBody ClientDTO client) {

        Client clientDomain = null;

        clientDomain = modelMapper.map(client, Client.class);

        pixService.save(clientDomain);

        return new ResponseEntity(OK);
    }

    @GetMapping(value = "/findById/{pixKeyId}")
    public ResponseEntity<PixResponseDTO> findById(@PathVariable String pixKeyId) {

        PixResponse response = pixService.findById(pixKeyId);

        PixResponseDTO responseDTO = modelMapper.map(response, PixResponseDTO.class);

        return ResponseEntity.ok().body(responseDTO);
    }
}
