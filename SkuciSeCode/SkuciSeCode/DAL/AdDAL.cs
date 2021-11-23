using PredicateExtensions;
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
            if (ad != null)
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

        public int EditAd(Ad ad)
        {
            int ind = -1;
            var ads = _context.Ads.ToList().Where(a => a.id == ad.id);
            Ad existingAd = ads.FirstOrDefault();
            if (existingAd != null)
            {
                existingAd.title = ad.title;
                existingAd.location = ad.location;
                existingAd.price = ad.price;
                existingAd.size = ad.size;
                existingAd.floor = ad.floor;
                existingAd.description = ad.description;
                existingAd.elevator = ad.elevator;
                existingAd.flat_house = ad.flat_house;
                existingAd.sell_rent = ad.sell_rent;
                existingAd.yard = ad.yard;
                existingAd.tv = ad.tv;
                existingAd.garage = ad.garage;
                existingAd.ac = ad.ac;
                existingAd.balcony = ad.balcony;
                existingAd.heating = ad.heating;
                existingAd.internet = ad.internet;
                existingAd.intercom = ad.intercom;
                existingAd.user_id = ad.user_id;

                ind = _context.SaveChanges();

            }
            return ind;
        }

        public List<Ad> GetAdsByUserId(int user_id)
        {
            var allUserAds = _context.Ads.ToList().Where(ad => ad.date_end == null && ad.user_id == user_id);
            var userAds = allUserAds.ToList();
            return userAds;
        }

        public List<Ad> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, String location, int internet, int ac, int heating, int tv)
        {
            var pr = PredicateExtensions.PredicateExtensions.Begin<Ad>();

            if (sell_rent >= 0)
            {
                pr = pr.And(ad => ad.sell_rent == sell_rent);
            }
            if (flat_house >= 0)
            {
                pr = pr.And(ad => ad.flat_house == flat_house);
            }
            if (to_number_of_rooms > 0)
            {
                pr = pr.And(ad => ad.number_of_rooms >= from_number_of_rooms && ad.number_of_rooms <= to_number_of_rooms);
            }
            if (to_size > 0)
            {
                pr = pr.And(ad => ad.size >= from_size && ad.size <= to_size);
            }
            if (to_price > 0)
            {
                pr = pr.And(ad => ad.price >= from_price && ad.price <= to_price);
            }
            if (location != null)
            {
                pr = pr.And(ad => ad.location.Contains(location));
            }
            if (internet != 0)
            {
                pr = pr.And(ad => ad.internet == internet);
            }
            if (ac != 0)
            {
                pr = pr.And(ad => ad.ac == ac);
            }
            if (heating != 0)
            {
                pr = pr.And(ad => ad.heating == heating);
            }
            if (tv != 0)
            {
                pr = pr.And(ad => ad.tv == tv);
            }

            var ads = _context.Ads.Where(pr);
            var filteredAds = ads.ToList();
            return filteredAds;
        }
    }
}
