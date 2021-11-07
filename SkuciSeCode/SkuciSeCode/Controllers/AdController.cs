using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SkuciSeCode.UI.Interfaces;

namespace SkuciSeCode.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdController : ControllerBase
    {
        private readonly IAdUI _iAdUI;
        public AdController(IAdUI iAdUI)
        {
            _iAdUI = iAdUI;
        }

        [HttpPost]
        [Route("AddNewAd")]
        public int AddNewAd([FromForm] String title, [FromForm] int flat_house, [FromForm] int sell_rent, [FromForm] String description, [FromForm] double size, [FromForm] String date_start, [FromForm] double price, [FromForm] String location)
        {
            //1+ - uspesno dodat oglas
            //0 - neuspesno registrovan korisnik
            int ind = _iAdUI.AddNewAd(title, flat_house, sell_rent, description, size, date_start, price, location);
            return ind;
        }
    }
}
