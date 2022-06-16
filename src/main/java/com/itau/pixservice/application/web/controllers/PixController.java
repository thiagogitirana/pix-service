package com.itau.pixservice.application.web.controllers;

import com.itau.pixservice.application.web.dto.request.ClientDTO;
import com.itau.pixservice.application.web.dto.response.PixResponseDTO;
import com.itau.pixservice.domain.entities.Client;
import com.itau.pixservice.domain.entities.PixResponse;
import com.itau.pixservice.domain.services.PixService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<List<PixResponseDTO>> addPix(@RequestBody ClientDTO client) {

        Client clientDomain = modelMapper.map(client, Client.class);

        List<PixResponse> pixs = pixService.save(clientDomain);

        List<PixResponseDTO> response = pixs
                .stream()
                .map(pix -> modelMapper.map(pix, PixResponseDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/findById/{pixKeyId}")
    public ResponseEntity<PixResponseDTO> findById(@PathVariable String pixKeyId) {

        PixResponse response = pixService.findById(pixKeyId);

        PixResponseDTO responseDTO = modelMapper.map(response, PixResponseDTO.class);

        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/{pixKeyId}")
    public ResponseEntity<PixResponseDTO> deleteById(@PathVariable String pixKeyId) {

        PixResponse response = pixService.remove(pixKeyId);

        PixResponseDTO responseDTO = modelMapper.map(response, PixResponseDTO.class);

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<List<PixResponseDTO>> find (
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "keyType", required = false) String keyType,
            @RequestParam(value = "branch", required = false) Integer branch,
            @RequestParam(value = "account", required = false) Integer account,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "insertDate", required = false) String insertDate,
            @RequestParam(value = "deleteDate", required = false) String deleteDate
    ) throws IllegalAccessException {
        List<PixResponse> pixs = pixService.find(id, keyType, branch, account, name, insertDate, deleteDate);

        List<PixResponseDTO> response = pixs
                .stream()
                .map(pix -> modelMapper.map(pix, PixResponseDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }
}
