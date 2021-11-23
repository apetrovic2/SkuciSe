using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkuciSeCode.Entities
{
    public class AdDbContext : DbContext
    {
        public DbSet<Ad> Ads { get; set; }
        public DbSet<AdImage> AdImages { get; set; }
        public AdDbContext(DbContextOptions<AdDbContext> options) : base(options)
        {

        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Ad>().ToTable("Ad");
            modelBuilder.Entity<AdImage>().ToTable("AdImage");
        }
    }
}
