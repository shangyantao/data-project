package com.sap.data.app.web.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sap.data.app.entity.event.E0001;
import com.sap.data.app.jms.NotifyMessageProducer;
import com.sap.data.app.service.event.E0001Manager;

@Controller
@RequestMapping(value = "/event/e0001")
public class E0001Controller {

	@Autowired
	private E0001Manager e0001Manager;
	
	@Autowired
	private NotifyMessageProducer notifyMessageProducer;
	
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		
		List<E0001> e0001s = e0001Manager.getAllPO();
		model.addAttribute("e0001s", e0001s);
		
		return "event/e0001List";
	}

	@RequestMapping(value = "findPOByCode",method=RequestMethod.POST)
	public String findPOByCode(@ModelAttribute("e0001") E0001 e0001, RedirectAttributes redirectAttributes,Model model) {

		System.out.println("111111111111111"+e0001.getRelGroup());
		notifyMessageProducer.sendQueue(e0001);
		List<E0001> es=e0001Manager.findPOByCode(e0001.getRelGroup(), e0001.getRelCode(), e0001.getItemsForRelease());
		model.addAttribute("e0001s", es);
		redirectAttributes.addFlashAttribute("message", "发送查询消息成功！");
		
		return "event/e0001List";
	}

}
