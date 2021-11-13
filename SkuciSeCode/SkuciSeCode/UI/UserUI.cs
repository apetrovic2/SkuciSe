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

        public User GetUserById(int id)
        {
            return _iUserBL.GetUserById(id);
        }

        public int Login(string username, string password)
        {
            int ind = _iUserBL.Login(username, password);
            return ind;
        }

        public int Registration(String username, String password, String name, String email)
        {
            int ind = _iUserBL.Registration(username, password, name, email);
            return ind;
        }

        public int EditUser(int id, String username, String password, String name, String email)
        {
            return _iUserBL.EditUser(id, username, password, name, email);
        }
        public int DeleteUser(int id)
        {
            return _iUserBL.DeleteUser(id);
        }
    }
}
