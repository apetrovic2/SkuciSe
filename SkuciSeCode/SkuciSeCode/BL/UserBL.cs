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

        public int Login(string username, string password)
        {
            int ind = -2;
            if(username != "" && password != "")
            {
                ind = _iUserDAL.Login(username, password);
            }
            return ind;
        }

        public int Registration(string username, string password, string name, string email)
        {
            int ind = -2;
            if (username != "" && password != "" && name != "" && email != "")
            {
                User user = new User(username, password, name, email);
                ind = _iUserDAL.Registration(user);
            }
            return ind;

        }
    }
}
