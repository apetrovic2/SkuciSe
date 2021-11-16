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
        public int AddNewAd([FromForm] String title, [FromForm] int flat_house, [FromForm] int sell_rent, [FromForm] int number_of_rooms, [FromForm] String description, [FromForm] float size, [FromForm] String date_start, [FromForm] float price, [FromForm] String location, [FromForm] int floor, [FromForm] int internet, [FromForm] int ac, [FromForm] int intercom, [FromForm] int garage, [FromForm] int elevator, [FromForm] int balcony, [FromForm] int yard, [FromForm] int heating, [FromForm] int tv)
        {
            //1+ - uspesno dodat oglas
            //0 - neuspesno dodat oglas
            int ind = _iAdUI.AddNewAd(title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
            return ind;
        }

        [HttpPut]
        [Route("EditAd")]
        public int EditAd([FromForm] int id, [FromForm] String title, [FromForm] int flat_house, [FromForm] int sell_rent, [FromForm] int number_of_rooms, [FromForm] String description, [FromForm] float size, [FromForm] String date_start, [FromForm] float price, [FromForm] String location, [FromForm] int floor, [FromForm] int internet, [FromForm] int ac, [FromForm] int intercom, [FromForm] int garage, [FromForm] int elevator, [FromForm] int balcony, [FromForm] int yard, [FromForm] int heating, [FromForm] int tv)
        {
            //1+ - uspesno editovan oglas
            //0 - nijedna vrednost nije promenjena
            int ind = _iAdUI.EditAd(id, title, flat_house, sell_rent, number_of_rooms, description, size, date_start, null, price, location, floor, internet, ac, intercom, garage, elevator, balcony, yard, heating, tv);
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
        public async Task<IActionResult> GetAdById([FromQuery] int id)
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
