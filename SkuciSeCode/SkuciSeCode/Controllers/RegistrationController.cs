using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SkuciSeCode.Entities;
using SkuciSeCode.Helpers;
using SkuciSeCode.UI.Interfaces;

namespace SkuciSeCode.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RegistrationController : ControllerBase
    {
        private readonly IUserUI _iUserUI;
        public RegistrationController(IUserUI iUserUI)
        {
            _iUserUI = iUserUI;
        }

        [HttpGet]
        [Route("GetAllUsers")]
        public async Task<IActionResult> Get()
        {
            var users = _iUserUI.GetAllUsers();
            var userModels = UserHelper.ConvertUsers(users);
            return Ok(userModels);
        }

        [HttpPost]
        [Route("Register")]
        public int Register([FromForm] String username, [FromForm] String password,[FromForm] String name,[FromForm] String email)
        {
            //1+ - uspesno registrovan korisnik
            //0 - neuspesno registrovan korisnik
            //-1 - korisnik sa datim username-om vec postoji
            //-2 - prazan string
            int ind = _iUserUI.Registration(username, password, name, email);
            return ind;
        }

        [HttpPost]
        [Route("Login")]
        public int Login([FromForm] String username, [FromForm] String password)
        {
            //1 - uspesno prijavljen korisnik
            //-3 - pogresan password
            //-1 - ne postoji korisnik sa datim username-om
            //-2 - prazan string
            //Console.WriteLine(user.username + " " + user.password);
            int ind = _iUserUI.Login(username, password);
            return ind;
        }
    }
}
