using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.BL.Interfaces
{
    public interface IUserBL
    {
        Task<List<User>> GetAllUsers();
        int Registration(String username, String password, String name, String email);
        int Login(String username, String password);
        int EditUser(int id, String username, String password, String name, String email, String image);
        int DeleteUser(int id);
        Task<User> GetUserById(int id);
        int ChangePassword(int id, String password);
        Task<UserImage> GetUserImage(int id);
        int SetProfilePicture(int user_id, String image);
    }
}
