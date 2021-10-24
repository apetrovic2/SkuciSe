using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL.Interfaces
{
    public class UserBL : IUserBL
    {
        private readonly IUserDAL _iUserDAL;
        public UserBL(IUserDAL iUserDAL)
        {
            _iUserDAL = iUserDAL;
        }
        public List<User> GetAllUsers()
        {
            return _iUserDAL.GetAllUsers();
        }
    }
}
