package com.main.excilys.controller.computer;

import com.main.excilys.presentation.Page;
import com.main.excilys.request.DashboardComputerRequest;
import com.main.excilys.service.ComputerService;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteComputerController {

  @Autowired
  private ComputerService computerService;

  /**
   * Dashboard mapping.
   *
   * @return
   *
   * @return the view
   */
  @PostMapping(value = "/deleteComputer")
  public ModelAndView doPost(@ModelAttribute DashboardComputerRequest dashboardRequest,
      @RequestParam("selection") String selection) {
    this.doDelete(selection);

    return new ModelAndView("redirect:/dashboard");
  }

  private void doDelete(String selection) {
    if (!selection.isEmpty()) {
      Arrays.asList(selection.split(",")).stream().filter(id -> id.matches("^[0-9]*$"))
          .collect(Collectors.toList()).forEach(id -> {
            computerService.deleteComputer(Long.valueOf(id));
            Page.decrementNbObject();
          });
    }

  }
}
