using Microsoft.EntityFrameworkCore;
using PredicateExtensions;
using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.Helpers;
using SkuciSeCode.Models;
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

        public async Task<List<AdWithImage>> GetAllAds(int category)
        {
            var ads = new List<AdModel>();
            if (category < 0)
            {
                var allAds = await _context.Ads.Include(a => a.images).Where(ad => ad.date_end == null).ToListAsync();
                ads = allAds;
            }
            else
            {
                var allAds = await _context.Ads.Include(a => a.images).Where(ad => ad.date_end == null && ad.sell_rent == category).ToListAsync();
                ads = allAds;
            }

            List<AdWithImage> adWithImages = new List<AdWithImage>();
            foreach(var ad in ads)
            {
                Ad a = new Ad(ad.id, ad.title, ad.flat_house, ad.sell_rent, ad.number_of_rooms, ad.description, ad.size,
                                ad.date_start, ad.date_end, ad.price, ad.location, ad.floor, ad.internet, ad.ac, ad.intercom,
                                ad.garage, ad.elevator, ad.balcony, ad.yard, ad.heating, ad.tv, ad.user_id);
                Ad add = new Ad();
                AdWithImage adImage = new AdWithImage(a, ad.images.image);
                adWithImages.Add(adImage);
            }

            return adWithImages;
        }

        public async Task<AdWithImage> GetAdById(int id)
        {
            var ads = await _context.Ads.Where(ad => ad.id == id).ToListAsync();
            AdModel ad = ads.FirstOrDefault();

            var adImages = await _context.AdImages.Where(a => a.ad_id == ad.id).ToListAsync();
            var adImage = adImages.FirstOrDefault();

            AdWithImage adWithImage = null;

            if (adImage != null)
            {
                Ad a = new Ad(ad.id, ad.title, ad.flat_house, ad.sell_rent, ad.number_of_rooms, ad.description, ad.size,
                                ad.date_start, ad.date_end, ad.price, ad.location, ad.floor, ad.internet, ad.ac, ad.intercom,
                                ad.garage, ad.elevator, ad.balcony, ad.yard, ad.heating, ad.tv, ad.user_id);
                adWithImage = new AdWithImage(a, adImage.image);
            }

            return adWithImage;
        }

        public async Task<int> AddNewAd(Ad ad)
        {
            int ind;
            AdModel adModel = AdHelper.ConvertAd(ad);
            await _context.Ads.AddAsync(adModel);
            int ind1 = _context.SaveChanges();
            if( ind1 > 0 )
            {
                ind = adModel.id;
            }
            else
            {
                ind = ind1;
            }
            return ind;
        }

        public int CloseAd(int id, String date_end)
        {
            int ind = -1;
            var ads = _context.Ads.ToList().Where(ad => ad.id == id);
            AdModel ad = ads.FirstOrDefault();
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
            AdModel ad = ads.FirstOrDefault();

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
            AdModel existingAd = ads.FirstOrDefault();
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

        public async Task<List<AdWithImage>> GetAdsByUserId(int user_id)
        {
            var allUserAds = await _context.Ads.Where(ad => ad.date_end == null && ad.user_id == user_id).ToListAsync();
            var userAds = allUserAds.ToList();
            List<AdWithImage> adsWithImages = new List<AdWithImage>();

            foreach (AdModel ad in userAds)
            {
                var adImages = await _context.AdImages.Where(a => a.ad_id == ad.id).ToListAsync();
                var adImage = adImages.FirstOrDefault();

                if (adImage != null)
                {
                    Ad a = new Ad(ad.id, ad.title, ad.flat_house, ad.sell_rent, ad.number_of_rooms, ad.description, ad.size,
                                ad.date_start, ad.date_end, ad.price, ad.location, ad.floor, ad.internet, ad.ac, ad.intercom,
                                ad.garage, ad.elevator, ad.balcony, ad.yard, ad.heating, ad.tv, ad.user_id);
                    AdWithImage adWithImage = new AdWithImage(a, adImage.image);
                    adsWithImages.Add(adWithImage);
                }
            }

            return adsWithImages;
        }

        public async Task<List<AdWithImage>> FilterAds(int sell_rent, int flat_house, int from_number_of_rooms, int to_number_of_rooms, float from_size, float to_size, float from_price, float to_price, String location, int internet, int ac, int heating, int tv)
        {
            var pr = PredicateExtensions.PredicateExtensions.Begin<AdModel>();

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

            pr = pr.And(ad => ad.date_end == null);

            var ads = await _context.Ads.Where(pr).ToListAsync();
            var filteredAds = ads;

            List<AdWithImage> adsWithImages = new List<AdWithImage>();

            foreach (AdModel ad in filteredAds)
            {
                var adImages = await _context.AdImages.Where(a => a.ad_id == ad.id).ToListAsync();
                var adImage = adImages.FirstOrDefault();

                if (adImage != null)
                {
                    Ad a = new Ad(ad.id, ad.title, ad.flat_house, ad.sell_rent, ad.number_of_rooms, ad.description, ad.size,
                                ad.date_start, ad.date_end, ad.price, ad.location, ad.floor, ad.internet, ad.ac, ad.intercom,
                                ad.garage, ad.elevator, ad.balcony, ad.yard, ad.heating, ad.tv, ad.user_id);
                    AdWithImage adWithImage = new AdWithImage(a, adImage.image);
                    adsWithImages.Add(adWithImage);
                }
            }

            return adsWithImages;
        }

        public async Task<int> SetAdPicture(int ad_id, String image)
        {
            //var bytes = Convert.FromBase64String(image);

            AdImage adImage = new AdImage(ad_id, image);
            AdImageModel adImageModel = AdImageHelper.ConvertAdImage(adImage);
            await _context.AdImages.AddAsync(adImageModel);
            int ind = _context.SaveChanges();
            return ind;
        }

        public async Task<int> MakeAnAppointment(int user_id, int ad_id, string date)
        {
                                                                    //approved - 0
            Appointment app = new Appointment(user_id, ad_id, date, 0);

            AppointmentModel appModel = AppointmentHelper.ConvertAppointment(app);
            await _context.Appointments.AddAsync(appModel);
            int ind = _context.SaveChanges();

            return ind;
        }

        public async Task<List<AppointmentModel>> GetAppointmentByOwnerId(int id)
        {
            var allApps = await _context.Appointments.Include(a => a.ad).Where(app => app.ad.user_id == id).ToListAsync();
            return allApps;
        }

        public int ApproveAppointment(int app_id)
        {
            var apps = _context.Appointments.ToList().Where(app => app.id == app_id);
            var app = apps.FirstOrDefault();

            app.approved = 1;

            int ind = _context.SaveChanges();

            return ind;
        }
    }
}
