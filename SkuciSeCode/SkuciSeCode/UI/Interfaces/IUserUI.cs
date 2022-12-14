using SkuciSeCode.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.UI.Interfaces
{
    public interface IUserUI
    {
        Task<List<User>> GetAllUsers();
        int Registration(String username, String password, String name, String email);
        int EditUser(int id, String username, String password, String name, String email, String image);
        int Login(String username, String password);
        int DeleteUser(int id);
        Task<User> GetUserById(int id);
        int ChangePassword(int id, String password);
        Task<UserImage> GetUserImage(int id);
        int SetProfilePicture(int user_id, String image);
    }
}
