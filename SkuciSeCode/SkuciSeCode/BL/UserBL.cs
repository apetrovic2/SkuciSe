using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;

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

        public User GetUserById(int id)
        {
            return _iUserDAL.GetUserById(id);
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

        public int Registration(string username, String password, string name, string email)
        {
            int ind = -2;
            if (username != "" && password != "" && name != "" && email != "")
            {

                var saltBytes = new byte[64];
                var provider = new RNGCryptoServiceProvider();
                provider.GetNonZeroBytes(saltBytes);

                var salt = Convert.ToBase64String(saltBytes);

                var rfc = new Rfc2898DeriveBytes(password, saltBytes, 10000);

                var hash = Convert.ToBase64String(rfc.GetBytes(256));

                User user = new User(username, hash, salt, name, email);

                Task<int> ind1;
                ind1 = _iUserDAL.RegistrationAsync(user);
                ind = ind1.Result;
            }
            return ind;

        }

        public int EditUser(int id, String username, String password, String name, String email)
        {
            return _iUserDAL.EditUser(id, username, password, name, email);
        }

        public int DeleteUser(int id)
        {
            return _iUserDAL.DeleteUser(id);
        }
    }
}
