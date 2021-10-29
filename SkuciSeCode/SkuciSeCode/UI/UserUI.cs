using SkuciSeCode.BL.Interfaces;
using SkuciSeCode.Entities;
using SkuciSeCode.UI.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI
{
    public class UserUI : IUserUI
    {
        private readonly IUserBL _iUserBL;
        public UserUI(IUserBL iUserBL)
        {
            _iUserBL = iUserBL;
        }
        public List<User> GetAllUsers()
        {
            return _iUserBL.GetAllUsers();
        }

        public int Login(string username, string password)
        {
            int ind = _iUserBL.Login(username, password);
            return ind;
        }

        public int Registration(string username, string password, string name, string email)
        {
            int ind = _iUserBL.Registration(username, password, name, email);
            return ind;
        }
    }
}
