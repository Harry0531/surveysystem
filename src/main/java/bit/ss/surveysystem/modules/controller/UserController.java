package bit.ss.surveysystem.modules.controller;

import bit.ss.surveysystem.common.web.BaseApi;
import bit.ss.surveysystem.common.web.MsgType;
import bit.ss.surveysystem.modules.Service.UserService;
import bit.ss.surveysystem.modules.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "isNameUnused", method = RequestMethod.POST)
    @ResponseBody
    public Object isNameUnused(@RequestParam("userName") String userName) {
        try {
            if (userService.isNameUnused(userName))
                return retMsg.Set(MsgType.SUCCESS, true);
            return retMsg.Set(MsgType.SUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody UserEntity user) {
        try {
            String result = userService.login(user);
            return retMsg.Set(MsgType.SUCCESS, result);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody UserEntity user) {
        try {
            if (userService.register(user))
                return retMsg.Set(MsgType.SUCCESS, true);
            return retMsg.Set(MsgType.SUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
            return retMsg.Set(MsgType.ERROR, e.toString());
        }
    }
}
