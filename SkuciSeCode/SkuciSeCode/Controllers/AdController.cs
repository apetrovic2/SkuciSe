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
    public class AdController : ControllerBase
    {
        private readonly IAdUI _iAdUI;
        public AdController(IAdUI iAdUI)
        {
            _iAdUI = iAdUI;
        }

        [HttpPost]
        [Route("AddNewAd")]
        public int AddNewAd([FromForm] String title, [FromForm] int flat_house, [FromForm] int sell_rent, [FromForm] String description, [FromForm] float size, [FromForm] String date_start, [FromForm] float price, [FromForm] String location)
        {
            //1+ - uspesno dodat oglas
            //0 - neuspesno registrovan korisnik
            int ind = _iAdUI.AddNewAd(title, flat_house, sell_rent, description, size, date_start, price, location);
            return ind;
        }

        [HttpGet]
        [Route("GetAllAds")]
        public async Task<IActionResult> GetAllAds([FromQuery] int category)
        {
            var ads = _iAdUI.GetAllAds(category);
            var adModels = AdHelper.ConvertUsers(ads);
            return Ok(adModels);
        }

        [HttpGet]
        [Route("GetAdById")]
        public async Task<IActionResult> GetAdById([FromForm] int id)
        {
            Ad ad = _iAdUI.GetAdById(id);
            return Ok(ad);
        }

        [HttpPut]
        [Route("CloseAd")]
        public int CloseAd([FromForm] int id, [FromForm] String date_end)
        {
            //-1 ne postoji oglas koji ima ovaj id
            //0 neuspesno
            //1 uspesno
            int ind = _iAdUI.CloseAd(id, date_end);
            return ind;
        }

        [HttpDelete]
        [Route("DeleteAd")]
        public int DeleteAd([FromForm] int id)
        {
            //-1 ne postoji oglas koji ima ovaj id
            //0 neuspesno
            //1 uspesno
            int ind = _iAdUI.DeleteAd(id);
            return ind;
        }

    }
}
