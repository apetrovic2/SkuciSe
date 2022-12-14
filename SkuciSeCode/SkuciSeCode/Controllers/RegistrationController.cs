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
            var users = await _iUserUI.GetAllUsers();
            var userModels = UserHelper.ConvertUsers(users);
            return Ok(userModels);
        }

        [HttpPost]
        [Route("Register")]
        public int Register([FromForm] String username, [FromForm] String password, [FromForm] String name, [FromForm] String email)
        {
            //1+ - uspesno registrovan korisnik
            //0 - neuspesno registrovan korisnik
            //-1 - korisnik sa datim username-om vec postoji
            //-2 - prazan string
            //-3 - korisnik sa datim email-om vec postoji
            int ind = _iUserUI.Registration(username, password, name, email);
            return ind;
        }

        [HttpPost]
        [Route("SetProfilePicture")]
        public int SetProfilePicture([FromForm] int user_id, [FromForm] String image)
        {
            int ind = _iUserUI.SetProfilePicture(user_id, image);
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

        [HttpPost]
        [Route("GetUserById")]
        public async Task<IActionResult> GetUserById([FromForm] int id)
        {
            User user = await _iUserUI.GetUserById(id);
            return Ok(user);
        }

        [HttpGet]
        [Route("GetUserImage")]
        public async Task<IActionResult> GetUserImage([FromQuery] int id)
        {
            UserImage userImage = await _iUserUI.GetUserImage(id);
            return Ok(userImage);
        }

        [HttpPut]
        [Route("EditUser")]
        public int EditUser([FromForm] int id, [FromForm] String username, [FromForm] String password, [FromForm] String name, [FromForm] String email, [FromForm] String image)
        {
            //-1 ne postoji korisnik koji ima ovaj id
            //-2 netacan password
            //-3 zauzet username
            //-4 zauzet email
            //0 neuspesno
            //1 uspesno
            int ind = _iUserUI.EditUser(id, username, password, name, email, image);
            return ind;
        }

        [HttpPut]
        [Route("ChangePassword")]
        public int ChangePassword([FromForm] int id, [FromForm] String password)
        {
            int ind = _iUserUI.ChangePassword(id, password);
            return ind;
        }

        [HttpDelete]
        [Route("DeleteUser")]
        public int DeleteUser([FromForm] int id)
        {
            //-1 ne postoji oglas koji ima ovaj id
            //0 neuspesno
            //1 uspesno
            int ind = _iUserUI.DeleteUser(id);
            return ind;
        }
    }
}
