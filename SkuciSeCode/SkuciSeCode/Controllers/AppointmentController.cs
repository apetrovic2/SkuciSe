using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SkuciSeCode.Entities;
using SkuciSeCode.UI.Interfaces;

namespace SkuciSeCode.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AppointmentController : ControllerBase
    {
        private readonly IAdUI _iAdUI;
        public AppointmentController(IAdUI iAdUI)
        {
            _iAdUI = iAdUI;
        }

        [HttpPost]
        [Route("MakeAppointment")]
        public int MakeAnAppointment([FromForm] int user_id, [FromForm] int ad_id, [FromForm] String date)
        {
            int ind = _iAdUI.MakeAnAppointment(user_id, ad_id, date);
            return ind;
        }

        [HttpGet]
        [Route("GetAppointmentByOwnerId")]
        public async Task<IActionResult> GetAppointmentByOwnerId([FromQuery] int id)
        {
            var app = await _iAdUI.GetAppointmentByOwnerId(id);
            return Ok(app);
            
        }

        [HttpPut]
        [Route("ApproveAppointment")]
        public int ApproveAppointment([FromForm] int app_id, [FromForm] int approve_status)
        {
            int ind =  _iAdUI.ApproveAppointment(app_id, approve_status);
            return ind;
        }

        [HttpGet]
        [Route("GetAppointmentResponse")]
        public async Task<IActionResult> GetAppointmentResponse([FromQuery] int id)
        {
            var app = await _iAdUI.GetAppointmentResponse(id);
            return Ok(app);

        }

    }
}
