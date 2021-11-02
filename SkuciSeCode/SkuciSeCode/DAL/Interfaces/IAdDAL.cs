using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL.Interfaces
{
    public interface IAdDAL
    {
        Task<int> AddNewAd(Ad ad);
    }
}
