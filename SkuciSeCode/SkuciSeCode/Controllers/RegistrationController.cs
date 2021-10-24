using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
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
        public async Task<IActionResult> Get()
        {
            var users = _iUserUI.GetAllUsers();
            var userModels = UserHelper.ConvertUsers(users);
            return Ok(userModels);
        }
    }
}
