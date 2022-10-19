package com.stefanini.aceleraDevs.controller;

import com.stefanini.aceleraDevs.controller.form.AlunoForm;
import com.stefanini.aceleraDevs.dto.AlunoDTO;
import com.stefanini.aceleraDevs.exception.TurmaNotFoundException;
import com.stefanini.aceleraDevs.model.Aluno;
import com.stefanini.aceleraDevs.mapper.AlunoDTOService;
import com.stefanini.aceleraDevs.service.AlunoService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoDTOService alunoDTOService;

    @Autowired
    public AlunoController(AlunoService alunoService, AlunoDTOService alunoDTOService) {
        this.alunoService = alunoService;
        this.alunoDTOService = alunoDTOService;
    }

    @Autowired

    @RequestMapping(path = "/aluno")
    public ModelAndView loadHtml() {

        ModelAndView mv = new ModelAndView("aluno");
        AlunoDTO alunoDTO = new AlunoDTO();

        mv.addObject("alunoDTO", alunoDTO);

        return mv;
    }

    @PostMapping(value = "/aluno")
    public String saveAluno(AlunoDTO aluno) throws TurmaNotFoundException {

        Aluno newAluno = alunoDTOService.mapAluno(aluno);

        alunoService.save(newAluno);

        return "redirect:/aluno";
    }

    @GetMapping("/alunos")
    public List<AlunoDTO> listaAlunos() {
        List<Aluno> alunos = alunoService.findAllAlunos();
        return AlunoDTO.converter(alunos);

    }

    @PostMapping("/alunos")
    public ResponseEntity<AlunoDTO> cadastrar(@RequestBody AlunoForm form, UriComponentsBuilder uriBuilder) {
        Aluno aluno = form.converter();
        alunoService.save(aluno);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDTO(aluno));
    }

}
