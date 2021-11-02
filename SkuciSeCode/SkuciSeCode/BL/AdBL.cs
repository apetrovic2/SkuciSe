using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL
{
    public class AdBL : IAdBL
    {
        private readonly IAdDAL _iAdDAL;
        public AdBL(IAdDAL iAdDAL)
        {
            _iAdDAL = iAdDAL;
        }

        public int AddNewAd(string title, int flat_house, int sell_rent, string description, double size, DateTime date_start, double price, string location)
        {
            Ad ad = new Ad(title, flat_house, sell_rent, 0, description, size, date_start, null, price, location);
            Task<int> ind =  _iAdDAL.AddNewAd(ad);
            int ind1 = ind.Result;
            return ind1;
        }
    }
}
