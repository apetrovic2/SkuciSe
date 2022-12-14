using SkuciSeCode.DAL.Interfaces;
using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Security.Cryptography;
using SkuciSeCode.Helpers;

namespace SkuciSeCode.BL.Interfaces
{
    public class UserBL : IUserBL
    {
        private readonly IUserDAL _iUserDAL;
        public UserBL(IUserDAL iUserDAL)
        {
            _iUserDAL = iUserDAL;
        }
        public Task<List<User>> GetAllUsers()
        {
            return _iUserDAL.GetAllUsers();
        }

        public Task<User> GetUserById(int id)
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
                var salt = PasswordHelper.getNewSalt(ref saltBytes);

                var hash = PasswordHelper.getPasswordHash(password, saltBytes);

                User user = new User(username, hash, salt, name, email);

                Task<int> ind1;
                ind1 = _iUserDAL.RegistrationAsync(user);
                ind = ind1.Result;
            }
            return ind;

        }

        public int EditUser(int id, String username, String password, String name, String email, String image)
        {
            return _iUserDAL.EditUser(id, username, password, name, email, image);
        }

        public int DeleteUser(int id)
        {
            return _iUserDAL.DeleteUser(id);
        }

        public int ChangePassword(int id, string password)
        {
            var saltBytes = new byte[64];
            var salt = PasswordHelper.getNewSalt(ref saltBytes);

            var hash = PasswordHelper.getPasswordHash(password, saltBytes);

            return _iUserDAL.ChangePassword(id, hash, salt);
        }

        public Task<UserImage> GetUserImage(int id)
        {
            return _iUserDAL.GetUserImage(id);
        }

        public int SetProfilePicture(int user_id, string image)
        {
            Task<int> ind1 =  _iUserDAL.SetProfilePicture(user_id, image);
            int ind = ind1.Result;
            return ind;
        }
    }
}
