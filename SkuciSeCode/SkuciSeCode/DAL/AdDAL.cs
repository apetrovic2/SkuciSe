using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL
{
    public class AdDAL : IAdDAL
    {
        private readonly AdDbContext _context;
        public AdDAL(AdDbContext context)
        {
            _context = context;
        }

        public async Task<int> AddNewAd(Ad ad)
        {
            int ind;
            await _context.Ads.AddAsync(ad);
            ind = _context.SaveChanges();
            return ind;
        }
    }
}
