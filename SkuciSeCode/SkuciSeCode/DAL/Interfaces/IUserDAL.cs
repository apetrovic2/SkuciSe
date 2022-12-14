using SkuciSeCode.Entities;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.DAL.Interfaces
{
    public interface IUserDAL
    {
        Task<List<User>> GetAllUsers();
        Task<int> RegistrationAsync(User user);
        int Login(String username, String password);
        int EditUser(int id, String username, String password, String name, String email, String image);
        int DeleteUser(int id);
        Task<User> GetUserById(int id);
        int ChangePassword(int id, String hash, String salt);
        Task<UserImage> GetUserImage(int id);
        Task<int> SetProfilePicture(int user_id, String image);
    }
}
