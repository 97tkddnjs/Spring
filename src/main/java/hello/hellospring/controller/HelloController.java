package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 웹 내부에서 즉, 외부에서 파라미터 받을 때는 @RequestParam 사용~
    public String helloMvc(@RequestParam(value ="name", required = true) String name, Model model) {
        // 파라미터에서 넘겨진 name을 넘겨
        model.addAttribute("name", name);
        return "hello-templates";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 헤더부와 바디부가 잇는 데 바디부에 데이터를 직접 넣겠다는 의미 html이 필요 X
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //name에 spring을 넣으면 hello spring이 되겠지
    }
    //이런 것을 APi 방식이라고 함!
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name; // private니까

        //getter setter에서 열고 닫는 다 property 접근 방식이라고도 함
        public String getName() {
            return name; //key는 name
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
