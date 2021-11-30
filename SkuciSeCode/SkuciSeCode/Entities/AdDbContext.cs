using Microsoft.EntityFrameworkCore;
using SkuciSeCode.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class AdDbContext : DbContext
    {
        public DbSet<AdModel> Ads { get; set; }
        public DbSet<AdImageModel> AdImages { get; set; }
        public DbSet<AppointmentModel> Appointments { get; set; }
        public AdDbContext(DbContextOptions<AdDbContext> options) : base(options)
        {

        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<AdModel>().ToTable("Ad");
            modelBuilder.Entity<AdImageModel>().ToTable("AdImage");
            modelBuilder.Entity<AppointmentModel>().ToTable("Appointment");
        }
    }
}
