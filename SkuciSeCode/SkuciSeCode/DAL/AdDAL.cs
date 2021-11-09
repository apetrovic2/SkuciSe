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

        public List<Ad> GetAllAds(int category)
        {
            var ads = new List<Ad>();
            if (category < 0)
            {
                var allAds = _context.Ads.ToList().Where(ad => ad.date_end == null);
                ads = allAds.ToList();
            }
            else
            {
                var allAds = _context.Ads.ToList().Where(ad => ad.date_end == null && ad.sell_rent == category);
                ads = allAds.ToList();
            }

            return ads;
        }

        public Ad GetAdById(int id)
        {
            var ads = _context.Ads.ToList().Where(ad => ad.id == id);
            Ad ad = ads.FirstOrDefault();
            return ad;
        }

        public async Task<int> AddNewAd(Ad ad)
        {
            int ind;
            await _context.Ads.AddAsync(ad);
            ind = _context.SaveChanges();
            return ind;
        }

        public int CloseAd(int id, String date_end)
        {
            int ind = -1;
            var ads = _context.Ads.ToList().Where(ad => ad.id == id);
            Ad ad = ads.FirstOrDefault();
            if(ad != null)
            {
                ad.date_end = date_end;
                ind = _context.SaveChanges();
            }
            return ind;
        }

        public int DeleteAd(int id)
        {
            int ind = -1;
            var ads = _context.Ads.ToList().Where(ad => ad.id == id);
            Ad ad = ads.FirstOrDefault();

            if (ad != null)
            {
                _context.Ads.Remove(ad);
                ind = _context.SaveChanges();
            }
            
            return ind;
        }
    }
}
