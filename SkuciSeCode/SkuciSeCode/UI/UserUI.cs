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
    }
}
